package Uebung11;

public class RsaFinn {
	private long q =72173131;
	private long p =188711;
	private long n = p * q;
	private long eulerFunc = (p - 1) * (q - 1);
	private long a, b;
	private long[] euklidAlgr = new long[3];

	public RsaFinn() {
		generatePublicKey();
		generatePrivateKey();
	}

	private static long[] erwEuklid(long a, long b) {
		if (b == 0)
			return new long[] { a, 1, 0 };
		else {
			long[] erg = new long[3];
			erg = erwEuklid(b, a % b);
			return new long[] { erg[0], erg[2], erg[1] - (erg[2] * (a / b)) };
		}
	}

	private long generatePublicKey() {
		boolean flag = true;
		while (flag) {
			a = (long) (Math.random() * eulerFunc);
			euklidAlgr = erwEuklid(a, eulerFunc);
			if (euklidAlgr[0] == 1) {
				flag = false;
			}
		}
		return a;
	}

	private long generatePrivateKey() {
		b = euklidAlgr[1];
		while (b < 0) {
			b += eulerFunc;
		}
		return b;
	}

	private long pow(long x, long y, long m) {
		long a;
		if (y == 0) {
			return 1;
		} else if ((y & 1) == 1) {
			a = pow(x, y - 1, m);
			return (a * x) % m;
		} else {
			a = pow(x, y/2, m);
			return (a * a) % m;
		}
	}
	
	private long naivePow(long x, long y, long m) {
		long z = 1;
		while(y>0) {
			z = (x*z)%m;
			y--;
		}
		return z;
	}

	private long encript(long m) {
		return pow(m, a, n);
	}
	
	private long decrypt(long c) {
		return pow(c,b,n);
	}

	public static void main(String[] args) {
		RsaFinn rsa = new RsaFinn();
		long m = 12816119204301l; 
		System.out.println(m);
		long c = rsa.encript(m);
		m = rsa.decrypt(c);
		System.out.println(m);
	}
}
