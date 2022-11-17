package taxcomparator.persistence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

import taxcomparator.model.Fasce;
import taxcomparator.model.Fascia;

public class MyFasceReader implements FasceReader {
/*
 * no-tax area:  8.174
 * 0-15.000:		23%
 * 15.000-28.000:	25%
 * 28.000-50.000:	35%
 * 50.000-oltre:	43%
*/
	private static NumberFormat deformatterNumber = NumberFormat.getNumberInstance(Locale.ITALY);
	private static NumberFormat deformatterPercent = NumberFormat.getPercentInstance(Locale.ITALIAN);
	
	@Override
	public Fasce readFasce(String descr, Reader reader) throws BadFileFormatException, IOException{
		if (reader == null || descr == null)
			throw new IllegalArgumentException("nullable input");
		deformatterPercent.setMinimumFractionDigits(0);
		BufferedReader BR = new BufferedReader(reader);

		String line = BR.readLine();
		if (line == null)
			throw new BadFileFormatException("Nullable file to read");
		
		StringTokenizer stk = new StringTokenizer(line, ":");
		if (stk.countTokens() != 2)
			throw new BadFileFormatException("not good number of tokens");
		if (!stk.nextToken().trim().toLowerCase().equals("no-tax area"))
			throw new BadFileFormatException("First line not: no-tax area");
		double noTax = 0;
		try {
			noTax = deformatterNumber.parse(stk.nextToken().trim()).doubleValue();
		}catch(ParseException e) {
				throw new BadFileFormatException("Error in lecture of no Tax area");
		}
		List<Fascia> lf = new ArrayList<Fascia>();
		boolean end = false;
		while((line = BR.readLine())!= null && !end) {
			stk = new StringTokenizer(line, "-:");
			if (stk.countTokens() != 3)
				throw new BadFileFormatException("not good number of tokens");
			try {
				double da = deformatterNumber.parse(stk.nextToken().trim()).doubleValue();
				String second = stk.nextToken().trim();
				double a = 0;
				if (second.equals("oltre")) {
					end = true;
				} else {
					a = deformatterNumber.parse(second).doubleValue();
				}
				double aliquota= deformatterPercent.parse(stk.nextToken().trim()).doubleValue();
//				System.out.println(da+" | "+ a+" | "+ aliquota);
				Fascia f;
				if (!end)
					f= new Fascia(da, a, aliquota);
				else 
					f = new Fascia(da, aliquota);
				lf.add(f);

			}catch(ParseException e) {
					throw new BadFileFormatException("Error in lecture of fascia");
			}
		}
		return new Fasce(descr, lf, noTax);
	}

}