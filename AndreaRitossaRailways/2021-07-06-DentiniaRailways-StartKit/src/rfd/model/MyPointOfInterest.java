package rfd.model;

public class MyPointOfInterest extends PointOfInterest {

	
	public MyPointOfInterest(String name, String progressivaKm) {
//		progressivaKM: 101+273
		super(name, progressivaKm);
		if (name == null || progressivaKm == null || this.getKm().equals("") || this.getStationName().equals("")) {
			throw new IllegalArgumentException("vuoto");
		}
		char [] arr = getKm().toCharArray();
		
		boolean ok1 = false;
		int j = 0, k = 0;
		for (int i = 0; i < arr.length; i++) {
			if (!ok1) {
				if (arr[i] == '+') ok1 = true;
				else
					j++;
			} else {
				k++;
			}
		}
		if (!ok1 || j < 1 || k != 3) {
			throw new IllegalArgumentException("not good params");
		}

	}

	@Override
	public double getKmAsNum() {
		boolean ok1 = false;
		String km = "";
		String n = "";
		char [] arr = this.getKm().toCharArray();
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '+' )
				ok1 = true;
			else if (!ok1)
				km += arr[i];
			else
				n+= arr[i];
		}
		
		if (!ok1)
			throw new IllegalArgumentException("Nullable km");
		
		try {
			return Double.parseDouble((km+n)) / 1000;
		} catch(Error e) {
			throw new Error(e.getLocalizedMessage());
		}
	}

}
