package com.enzenith.utils.image.gif;

/**
 *Quant
 * @author: Shupeng Lin
 * @date: 2019-11-14  上午 10:48
 **/
public class Quant {
    /** number of colours used */
    protected static final int  NET_SIZE = 256;

    /* four primes near 500 - assume no image has a length so large */
    /** that it is divisible by all four primes */
    protected static final int PRIME_ONE = 499;
    protected static final int PRIME_TWO = 491;
    protected static final int PRIME_THREE = 487;
    protected static final int PRIME_FOUR = 503;

    protected static final int  MIN_PICTURE_BYTES = (3 * PRIME_FOUR);
	/* minimum size for input image */

	/* Program Skeleton
	   ----------------
	   [select samplefac in range 1..30]
	   [read image from input file]
	   pic = (unsigned char*) malloc(3*width*height);
	   initnet(pic,3*width*height,samplefac);
	   learn();
	   unbiasnet();
	   [write output image header, using writecolourmap(f)]
	   inxbuild();
	   write output image using inxsearch(b,g,r)      */

	/* Network Definitions
	   ------------------- */

    protected static final int  MAX_NET_POS = (NET_SIZE - 1);
    /** bias for colour values */
    protected static final int  NET_BIASSHIFT = 4;
    /** no. of learning cycles */
    protected static final int NCYCLES = 100;

    /** defs for freq and bias */
    protected static final int INTBIASSHIFT = 16;
    /** bias for fractions */
    protected static final int INTBIAS = (((int) 1) << INTBIASSHIFT);
    /** gamma = 1024 */
    protected static final int GAMMASHIFT = 10;
    protected static final int GAMMA = (((int) 1) << GAMMASHIFT);
    protected static final int  BETASHIFT = 10;
    /** beta = 1/1024 */
    protected static final int BETA = (INTBIAS >> BETASHIFT);
    protected static final int BETA_GAMMA =
            (INTBIAS << (GAMMASHIFT - BETASHIFT));

    /** defs for decreasing radius factor */
    /** for 256 cols, radius starts */
    protected static final int  INITRAD = (NET_SIZE >> 3);
    /** at 32.0 biased by 6 bits */
    protected static final int  RADIUSBIASSHIFT = 6;
    protected static final int  RADIUSBIAS = (((int) 1) << RADIUSBIASSHIFT);
    /** and decreases by a */
    protected static final int  INITRADIUS = (INITRAD * RADIUSBIAS);
    /** factor of 1/30 each cycle */
    protected static final int  RADIUSDEC = 30;

    /* defs for decreasing alpha factor */
    /** alpha starts at 1.0 */
    protected static final int  ALPHABIASSHIFT = 10;
    protected static final int INITALPHA = (((int) 1) << ALPHABIASSHIFT);

    /** biased by 10 bits */
    protected int  laphadec;

    /** radbias and alpharadbias used for radpower calculation */
    protected static final int RAD_BIASSHIFT = 8;
    protected static final int RADBIAS = (((int) 1) << RAD_BIASSHIFT);
    protected static final int  ALPHARADBSHIFT = (ALPHABIASSHIFT + RAD_BIASSHIFT);
    protected static final int  ALPHARADBIAS = (((int) 1) << ALPHARADBSHIFT);

	/* Types and Global Variables
	-------------------------- */

    /** the input image itself */
    protected byte[]  tepicTure;
    /*** lengthcount = H*W*3 */
    protected int  lengthCount;

    /** sampling factor 1..30 */
    protected int  samplefac;

    //   typedef int pixel[4];                /** BGRc */
    /** the network itself - [NET_SIZE][4] */
    protected int[][]  netWork;

    protected int[] netIndex = new int[256];
	/** for network lookup - really 256 */

    protected int[] bias = new int[NET_SIZE];
    /** bias and freq arrays for learning */
    protected int[] freq = new int[NET_SIZE];
    protected int[] radpower = new int[INITRAD];
	/* radpower for precomputation */

    /* Initialise network in range (0,0,0) to (255,255,255) and set parameters
       ----------------------------------------------------------------------- */
    public Quant(byte[] thepic, int len, int sample) {

        int i;
        int[] p;

        tepicTure = thepic;
        lengthCount = len;
        samplefac = sample;

        netWork = new int[NET_SIZE][];
        for (i = 0; i < NET_SIZE; i++) {
            netWork[i] = new int[4];
            p = netWork[i];
            p[0] = p[1] = p[2] = (i << (NET_BIASSHIFT + 8)) / NET_SIZE;
            /* 1/NET_SIZE */
            freq[i] = INTBIAS / NET_SIZE;
            bias[i] = 0;
        }
    }

    public byte[] colorMap() {
        byte[] map = new byte[3 * NET_SIZE];
        int[] index = new int[NET_SIZE];
        for (int i = 0; i < NET_SIZE; i++) {
            index[netWork[i][3]] = i;
        }
        int k = 0;
        for (int i = 0; i < NET_SIZE; i++) {
            int j = index[i];
            map[k++] = (byte) (netWork[j][0]);
            map[k++] = (byte) (netWork[j][1]);
            map[k++] = (byte) (netWork[j][2]);
        }
        return map;
    }

    /* Insertion sort of netWork and building of netindex[0..255] (to do after unbias)
       ------------------------------------------------------------------------------- */
    public void inxbuild() {

        int i, j, smallpos, smallval;
        int[] p;
        int[] q;
        int previouscol, startpos;

        previouscol = 0;
        startpos = 0;
        for (i = 0; i < NET_SIZE; i++) {
            p = netWork[i];
            smallpos = i;
            /* index on g */
            smallval = p[1];
			/* find smallest in i..NET_SIZE-1 */
            for (j = i + 1; j < NET_SIZE; j++) {
                q = netWork[j];
                /* index on g */
                if (q[1] < smallval) {
                    smallpos = j;
                    /* index on g */
                    smallval = q[1];
                }
            }
            q = netWork[smallpos];
			/* swap p (i) and q (smallpos) entries */
            if (i != smallpos) {
                j = q[0];
                q[0] = p[0];
                p[0] = j;
                j = q[1];
                q[1] = p[1];
                p[1] = j;
                j = q[2];
                q[2] = p[2];
                p[2] = j;
                j = q[3];
                q[3] = p[3];
                p[3] = j;
            }
			/* smallval entry is now in position i */
            if (smallval != previouscol) {
                netIndex[previouscol] = (startpos + i) >> 1;
                for (j = previouscol + 1; j < smallval; j++) {
                    netIndex[j] = i;
                }
                previouscol = smallval;
                startpos = i;
            }
        }
        netIndex[previouscol] = (startpos + MAX_NET_POS) >> 1;
        for (j = previouscol + 1; j < NET_SIZE; j++) {
            /* really 256 */
            netIndex[j] = MAX_NET_POS;
        }
    }

    /* Main Learning Loop
       ------------------ */
    public void learn() {

        int i, j, b, g, r;
        int radius, rad, alpha, step, delta, samplepixels;
        byte[] p;
        int pix, lim;

        if (lengthCount < MIN_PICTURE_BYTES) {
            samplefac = 1;
        }
        laphadec = 30 + ((samplefac - 1) / 3);
        p = tepicTure;
        pix = 0;
        lim = lengthCount;
        samplepixels = lengthCount / (3 * samplefac);
        delta = samplepixels / NCYCLES;
        alpha = INITALPHA;
        radius = INITRADIUS;

        rad = radius >> RADIUSBIASSHIFT;
        if (rad <= 1) {
            rad = 0;
        }
        for (i = 0; i < rad; i++) {
            radpower[i] =
                    alpha * (((rad * rad - i * i) * RADBIAS) / (rad * rad));
        }

        //fprintf(stderr,"beginning 1D learning: initial radius=%d\n", rad);

        if (lengthCount < MIN_PICTURE_BYTES) {
            step = 3;
        } else if ((lengthCount % PRIME_ONE) != 0) {
            step = 3 * PRIME_ONE;
        } else {
            if ((lengthCount % PRIME_TWO) != 0) {
                step = 3 * PRIME_TWO;
            } else {
                if ((lengthCount % PRIME_THREE) != 0) {
                    step = 3 * PRIME_THREE;
                } else {
                    step = 3 * PRIME_FOUR;
                }
            }
        }

        i = 0;
        while (i < samplepixels) {
            b = (p[pix + 0] & 0xff) << NET_BIASSHIFT;
            g = (p[pix + 1] & 0xff) << NET_BIASSHIFT;
            r = (p[pix + 2] & 0xff) << NET_BIASSHIFT;
            j = contest(b, g, r);

            altersingle(alpha, j, b, g, r);
            if (rad != 0) {
                /* alter neighbours */
                alterneigh(rad, j, b, g, r);
            }

            pix += step;
            if (pix >= lim) {
                pix -= lengthCount;
            }

            i++;
            if (delta == 0) {
                delta = 1;
            }
            if (i % delta == 0) {
                alpha -= alpha / laphadec;
                radius -= radius / RADIUSDEC;
                rad = radius >> RADIUSBIASSHIFT;
                if (rad <= 1) {
                    rad = 0;
                }
                for (j = 0; j < rad; j++) {
                    radpower[j] =
                            alpha * (((rad * rad - j * j) * RADBIAS) / (rad * rad));
                }
            }
        }
        //fprintf(stderr,"finished 1D learning: final alpha=%f !\n",((float)alpha)/initalpha);
    }

    /* Search for BGR values 0..255 (after net is unbiased) and return colour index
       ---------------------------------------------------------------------------- */
    public int map(int b, int g, int r) {

        int i, j, dist, a, bestd;
        int[] p;
        int best;

        /* biggest possible dist is 256*3 */
        bestd = 1000;
        best = -1;
        /* index on g */
        i = netIndex[g];
        /* start at netindex[g] and work outwards */
        j = i - 1;

        while ((i < NET_SIZE) || (j >= 0)) {
            if (i < NET_SIZE) {
                p = netWork[i];
                /* inx key */
                dist = p[1] - g;
                if (dist >= bestd) {
                    /* stop iter */
                    i = NET_SIZE;
                } else {
                    i++;
                    if (dist < 0) {
                        dist = -dist;
                    }
                    a = p[0] - b;
                    if (a < 0) {
                        a = -a;
                    }
                    dist += a;
                    if (dist < bestd) {
                        a = p[2] - r;
                        if (a < 0) {
                            a = -a;
                        }
                        dist += a;
                        if (dist < bestd) {
                            bestd = dist;
                            best = p[3];
                        }
                    }
                }
            }
            if (j >= 0) {
                p = netWork[j];
                /* inx key - reverse dif */
                dist = g - p[1];
                if (dist >= bestd) {
                    /* stop iter */
                    j = -1;
                } else {
                    j--;
                    if (dist < 0) {
                        dist = -dist;
                    }
                    a = p[0] - b;
                    if (a < 0) {
                        a = -a;
                    }
                    dist += a;
                    if (dist < bestd) {
                        a = p[2] - r;
                        if (a < 0) {
                            a = -a;
                        }
                        dist += a;
                        if (dist < bestd) {
                            bestd = dist;
                            best = p[3];
                        }
                    }
                }
            }
        }
        return (best);
    }

    public byte[] process() {
        learn();
        unbiasnet();
        inxbuild();
        return colorMap();
    }

    /* Unbias netWork to give byte values 0..255 and record position i to prepare for sort
       ----------------------------------------------------------------------------------- */
    public void unbiasnet() {

        int i, j;

        for (i = 0; i < NET_SIZE; i++) {
            netWork[i][0] >>= NET_BIASSHIFT;
            netWork[i][1] >>= NET_BIASSHIFT;
            netWork[i][2] >>= NET_BIASSHIFT;
            /* record colour no */
            netWork[i][3] = i;
        }
    }

    /* Move adjacent neurons by precomputed alpha*(1-((i-j)^2/[r]^2)) in radpower[|i-j|]
       --------------------------------------------------------------------------------- */
    protected void alterneigh(int rad, int i, int b, int g, int r) {

        int j, k, lo, hi, a, m;
        int[] p;

        lo = i - rad;
        if (lo < -1) {
            lo = -1;
        }
        hi = i + rad;
        if (hi > NET_SIZE) {
            hi = NET_SIZE;
        }

        j = i + 1;
        k = i - 1;
        m = 1;
        while ((j < hi) || (k > lo)) {
            a = radpower[m++];
            if (j < hi) {
                p = netWork[j++];
                try {
                    p[0] -= (a * (p[0] - b)) / ALPHARADBIAS;
                    p[1] -= (a * (p[1] - g)) / ALPHARADBIAS;
                    p[2] -= (a * (p[2] - r)) / ALPHARADBIAS;
                } catch (Exception e) {
                } // prevents 1.3 miscompilation
            }
            if (k > lo) {
                p = netWork[k--];
                try {
                    p[0] -= (a * (p[0] - b)) / ALPHARADBIAS;
                    p[1] -= (a * (p[1] - g)) / ALPHARADBIAS;
                    p[2] -= (a * (p[2] - r)) / ALPHARADBIAS;
                } catch (Exception e) {
                }
            }
        }
    }

    /* Move neuron i towards biased (b,g,r) by factor alpha
       ---------------------------------------------------- */
    protected void altersingle(int alpha, int i, int b, int g, int r) {

		/* alter hit neuron */
        int[] n = netWork[i];
        n[0] -= (alpha * (n[0] - b)) / INITALPHA;
        n[1] -= (alpha * (n[1] - g)) / INITALPHA;
        n[2] -= (alpha * (n[2] - r)) / INITALPHA;
    }

    /* Search for biased BGR values
       ---------------------------- */
    protected int contest(int b, int g, int r) {

		/* finds closest neuron (min dist) and updates freq */
		/* finds best neuron (min dist-bias) and returns position */
		/* for frequently chosen neurons, freq[i] is high and bias[i] is negative */
		/* bias[i] = gamma*((1/NET_SIZE)-freq[i]) */

        int i, dist, a, biasdist, betafreq;
        int bestpos, bestbiaspos, bestd, bestbiasd;
        int[] n;

        bestd = ~(((int) 1) << 31);
        bestbiasd = bestd;
        bestpos = -1;
        bestbiaspos = bestpos;

        for (i = 0; i < NET_SIZE; i++) {
            n = netWork[i];
            dist = n[0] - b;
            if (dist < 0) {
                dist = -dist;
            }
            a = n[1] - g;
            if (a < 0) {
                a = -a;
            }
            dist += a;
            a = n[2] - r;
            if (a < 0) {
                a = -a;
            }
            dist += a;
            if (dist < bestd) {
                bestd = dist;
                bestpos = i;
            }
            biasdist = dist - ((bias[i]) >> (INTBIASSHIFT - NET_BIASSHIFT));
            if (biasdist < bestbiasd) {
                bestbiasd = biasdist;
                bestbiaspos = i;
            }
            betafreq = (freq[i] >> BETASHIFT);
            freq[i] -= betafreq;
            bias[i] += (betafreq << GAMMASHIFT);
        }
        freq[bestpos] += BETA;
        bias[bestpos] -=  BETA_GAMMA;
        return (bestbiaspos);
    }
}
