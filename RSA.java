package lab4;

import java.math.BigInteger;

public class RSA {
    static class RSAKey {
        private PubKey pubkey;
        private PrivateKey privKey;

        private RSAKey() {
            this.pubkey = new PubKey();
            this.privKey = new PrivateKey();
        }

        public RSAKey(BigInteger p, BigInteger q, long e) {
            this();
            this.pubkey = new PubKey(p, q, e);
            this.privKey = new PrivateKey(this.pubkey, p, q);
        }

        public RSAKey(int p, int q, long e) {
            this(BigInteger.valueOf(p), BigInteger.valueOf(q), e);
        }

        /**
         * Returns pair of pubic key and exponent
         *
         * @return publicKey, exponent
         */
        public Pair<BigInteger, Long> getPubKey() {
            return new Pair<>(pubkey.n, pubkey.e);
        }

        /**
         * Returns pair of private and public keys
         *
         * @return privateKey, publicKey
         */
        public Pair<BigInteger, BigInteger> getPrivKey() {
            return new Pair<>(privKey.d, privKey.pub.n);
        }

        BigInteger crypt(BigInteger message) {
            return RSACrypt.crypt(this.getPubKey(), message);
        }

        BigInteger decrypt(BigInteger cryptotext) {
            return RSACrypt.decrypt(this.getPrivKey(), cryptotext);
        }

        private class PubKey {
            private BigInteger n;
            private long e;

            private PubKey() {

            }

            PubKey(BigInteger n, long e) {
                this.n = n;
                this.e = e;
            }

            PubKey(BigInteger p, BigInteger q, long e) {
                this(p.multiply(q), e);
            }
        }

        private class PrivateKey {
            private PubKey pub;
            private BigInteger d;

            private BigInteger p;
            private BigInteger q;

            private PrivateKey() {

            }

            PrivateKey(PubKey pub, BigInteger p, BigInteger q) {
                this.pub = pub;
                this.p = p;
                this.q = q;
                this.compute();
            }

            void compute() {
                BigInteger p_m_1 = this.p.subtract(BigInteger.ONE);
                BigInteger q_m_1 = this.q.subtract(BigInteger.ONE);
                BigInteger tmp = p_m_1.multiply(q_m_1);
                this.d = BigInteger.valueOf(this.pub.e).modInverse(tmp);
            }
        }
    }

    static class RSACrypt {
        public static BigInteger crypt(Pair<BigInteger, Long> publicKey, BigInteger message) {
            return message.modPow(BigInteger.valueOf(publicKey.getSecond()), publicKey.getFirst());
        }

        public static BigInteger decrypt(Pair<BigInteger, BigInteger> privateKey, BigInteger cryptotext) {
            return cryptotext.modPow(privateKey.getFirst(), privateKey.getSecond());
        }
    }
}
