package mytv.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.io.StringReader;
import java.time.Duration;
import java.util.SortedSet;

import mytv.model.Programme;
import mytv.persistence.BadFileFormatException;
import mytv.persistence.MyProgrammeReader;

import org.junit.Test;

public class MyProgrammeReaderTests {

	@Test
	public void testReadAll() throws IOException, BadFileFormatException {
		String[] descriptions = { "Beautiful Episodio 1143243",
								  "Indiana Jones e l'ultima crociata",				
								  "Star Trek TNG Stagione 2 Episodio 7",
								  "Star Trek TNG Stagione 2 Episodio 8"
		};
		Duration[] durations = { Duration.ofMinutes(5).plusHours(1),
								 Duration.ofMinutes(45),
								 Duration.ofMinutes(45),
								 Duration.ofMinutes(6).plusHours(2)
		};
		String toRead = descriptions[0] + "\t\t\t1:05\n"
				+ descriptions[1] +	"\t\t0:45\n"
				+ descriptions[2] +	"\t0:45\n"
				+ descriptions[3] +	"\t\t2:06\n";
		
		MyProgrammeReader reader = new MyProgrammeReader();
		SortedSet<Programme> prgs = reader.readAll(new StringReader(toRead));
		
		assertEquals(4, prgs.size());
		
		int i=0;
		for (Programme p : prgs){
			assertEquals(descriptions[i], p.getTitle());
			assertEquals(durations[i], p.getDuration());		
			i++;
		}
		
		/*
		assertEquals("Beautiful Episodio 1143243", prgs.first().getTitle());
		assertEquals(Duration.ofMinutes(5).plusHours(1), prgs.first().getDuration());

		assertEquals("Star Trek TNG Stagione 2 Episodio 7", prgs.get(1).getTitle());
		assertEquals(Duration.ofMinutes(45), prgs.get(1).getDuration());

		assertEquals("Star Trek TNG Stagione 2 Episodio 8", prgs.get(2).getTitle());
		assertEquals(Duration.ofMinutes(45), prgs.get(2).getDuration());

		assertEquals("Indiana Jones e l'ultima crociata", prgs.get(3).getTitle());
		assertEquals(Duration.ofMinutes(6).plusHours(2), prgs.get(3).getDuration());
		*/
	}

}
