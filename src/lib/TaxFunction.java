package lib;

public class TaxFunction {

	
	/**
	 * Fungsi untuk menghitung jumlah pajak penghasilan pegawai yang harus dibayarkan setahun.
	 * 
	 * Pajak dihitung sebagai 5% dari penghasilan bersih tahunan (gaji dan pemasukan bulanan lainnya dikalikan jumlah bulan bekerja dikurangi pemotongan) dikurangi penghasilan tidak kena pajak.
	 * 
	 * Jika pegawai belum menikah dan belum punya anak maka penghasilan tidak kena pajaknya adalah Rp 54.000.000.
	 * Jika pegawai sudah menikah maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000.
	 * Jika pegawai sudah memiliki anak maka penghasilan tidak kena pajaknya ditambah sebesar Rp 4.500.000 per anak sampai anak ketiga.
	 * 
	 */
	
	
	public static int calculateTax(int monthlySalary, int otherMonthlyIncome, int numberOfMonthWorking, int deductible, boolean isMarried, int numberOfChildren) {
		
		int tax = 0;
		
		if (numberOfMonthWorking > 12) {
			System.err.println("More than 12 month working per year");
		}
		
		if (numberOfChildren > 3) {
			numberOfChildren = 3;
		}
		
		int nonTaxableIncome = calculateNonTaxableIncome(isMarried, numberOfChildren);

        int yearlyIncome = (monthlySalary + otherMonthlyIncome) * numberOfMonthWorking;
        int taxableIncome = yearlyIncome - deductible - nonTaxableIncome;

        tax = (int) Math.round(0.05 * taxableIncome);

        return Math.max(tax, 0);
    }

    private static int calculateNonTaxableIncome(boolean isMarried, int numberOfChildren) {
        final int BASE = 54000000;
        final int MARRIED = 4500000;
        final int HAVE_CHILD = 1500000;

        int nonTaxableIncome = BASE;
        if (isMarried) {
            nonTaxableIncome += MARRIED + (numberOfChildren * HAVE_CHILD);
        }

        return nonTaxableIncome;
    }
	
}
