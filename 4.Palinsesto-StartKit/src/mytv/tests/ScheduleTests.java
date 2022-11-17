package mytv.tests;

import static org.junit.Assert.*;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.Test;

import mytv.model.*;

public class ScheduleTests {

	@Test
	public void testSchedule() {
		LocalDateTime start = LocalDateTime.now();
		new Schedule(start);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testSchedule_ShouldFailWithNullStart() {
		LocalDateTime start = null;
		new Schedule(start);
	}

	@Test
	public void testGetStartTime() {
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);

		assertEquals(start, s.getStartTime());
	}

	@Test
	public void testGetScheduledProgrammes_ShouldBeEmptyAfterConstruction() {
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);

		assertEquals(0, s.getScheduledProgrammes().size());
	}

	@Test
	public void testGetEndTime() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));

		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		assertEquals(start.plus(Duration.ofMinutes(6)), s.getEndTime());
	}
	

	@Test
	public void testGetEndTime_ShouldReturnStartTimeIfEmpty() {
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		assertEquals(start, s.getEndTime());
	}

	@Test
	public void testAdd() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));

		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);

		assertEquals(3, s.getScheduledProgrammes().size());

		List<ScheduledProgramme> sps = s.getScheduledProgrammes();
		assertEquals(start, sps.get(0).getStartTime());
		assertEquals(start.plus(Duration.ofMinutes(1)), sps.get(1)
				.getStartTime());
		assertEquals(start.plus(Duration.ofMinutes(3)), sps.get(2)
				.getStartTime());
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testAdd_ShouldFailWhenAddingNull() {
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(null);
	}
	
	@Test
	public void testRemove_Last() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
		
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		ScheduledProgramme removed;
		
		removed = s.remove(2);
		assertSame(p3, removed.getProgramme());
		assertEquals(2, s.getScheduledProgrammes().size());
		assertSame(p1, s.getScheduledProgrammes().get(0).getProgramme());
		assertSame(p2, s.getScheduledProgrammes().get(1).getProgramme());
		assertEquals(start.plus(Duration.ofMinutes(3)), s.getEndTime());
	}
	
	@Test
	public void testRemove_First() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
		
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		ScheduledProgramme removed;
		
		removed = s.remove(0);
		assertSame(p1, removed.getProgramme());
		assertSame(p2, s.getScheduledProgrammes().get(0).getProgramme());
		assertEquals(start, s.getScheduledProgrammes().get(0).getStartTime());
		assertSame(p3, s.getScheduledProgrammes().get(1).getProgramme());
		assertEquals(start.plus(Duration.ofMinutes(0)), s.getScheduledProgrammes().get(1).getStartTime());
		assertEquals(start.plus(Duration.ofMinutes(5)), s.getEndTime());
	}
	
	@Test
	public void testRemove_Intermediate() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
		
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		ScheduledProgramme removed;
		
		removed = s.remove(1);
		assertSame(p2, removed.getProgramme());
		assertSame(p1, s.getScheduledProgrammes().get(0).getProgramme());
		assertEquals(start, s.getScheduledProgrammes().get(0).getStartTime());
		assertSame(p3, s.getScheduledProgrammes().get(1).getProgramme());
		assertEquals(start.plus(Duration.ofMinutes(1)), s.getScheduledProgrammes().get(1).getStartTime());
		assertEquals(start.plus(Duration.ofMinutes(4)), s.getEndTime());
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemove_OutOfRange_Low() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
		
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		s.remove(-1);
	}
	
	@Test(expected=IndexOutOfBoundsException.class)
	public void testRemove_OutOfRange_High() {
		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
		
		LocalDateTime start = LocalDateTime.now();
		Schedule s = new Schedule(start);
		s.add(p1);
		s.add(p2);
		s.add(p3);
		
		s.remove(3);
	}

//	@Test
//	public void testInsertBefore() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
//		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
//
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p3);
//		s.insertBefore(s.getScheduledProgrammes().get(0), p1);
//		s.insertBefore(s.getScheduledProgrammes().get(1), p2);
//		
//		List<ScheduledProgramme> sps = s.getScheduledProgrammes();
//		assertEquals(p1, sps.get(0).getProgramme());
//		assertEquals(p2, sps.get(1).getProgramme());
//		assertEquals(p3, sps.get(2).getProgramme());
//
//		assertEquals(3, sps.size());
//		
//		assertEquals(start, sps.get(0).getStartTime());
//		assertEquals(start.plus(Duration.ofMinutes(1)), sps.get(1)
//				.getStartTime());
//		assertEquals(start.plus(Duration.ofMinutes(3)), sps.get(2)
//				.getStartTime());
//	}
//
//	@Test(expected=IllegalArgumentException.class)
//	public void testInsertBefore_ShouldFailWhenReferenceIsNonExisting() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
//		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
//
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p1);
//		
//		ScheduledProgramme px = new ScheduledProgramme(p2, LocalDateTime.now());
//		s.insertBefore(px, p3);
//	}
//
//	@Test(expected=IllegalArgumentException.class)
//	public void testInsertBefore_ShouldFailWhenInsertingNull() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p1);
//		
//		s.insertBefore(s.getScheduledProgrammes().get(0), null);
//	}
//
//
//	@Test
//	public void testInsertAfter() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
//		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
//
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p1);
//		s.insertAfter(s.getScheduledProgrammes().get(0), p3);
//		s.insertAfter(s.getScheduledProgrammes().get(0), p2);
//		
//		List<ScheduledProgramme> sps = s.getScheduledProgrammes();
//		assertEquals(p1, sps.get(0).getProgramme());
//		assertEquals(p2, sps.get(1).getProgramme());
//		assertEquals(p3, sps.get(2).getProgramme());
//		assertEquals(3, sps.size());
//		
//		assertEquals(3, sps.size());
//
//		assertEquals(start, sps.get(0).getStartTime());
//		assertEquals(start.plus(Duration.ofMinutes(1)), sps.get(1)
//				.getStartTime());
//		assertEquals(start.plus(Duration.ofMinutes(3)), sps.get(2)
//				.getStartTime());
//	}
//
//	@Test(expected=IllegalArgumentException.class)
//	public void testInsertAfter_ShouldFailWhenReferenceIsNonExisting() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		Programme p2 = new Programme("P2", Duration.ofMinutes(2));
//		Programme p3 = new Programme("P3", Duration.ofMinutes(3));
//
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p1);
//		
//		ScheduledProgramme px = new ScheduledProgramme(p2, LocalDateTime.now());
//		s.insertAfter(px, p3);
//	}
//
//	@Test(expected=IllegalArgumentException.class)
//	public void testInsertAfter_ShouldFailWhenInsertingNull() {
//		Programme p1 = new Programme("P1", Duration.ofMinutes(1));
//		
//		LocalDateTime start = LocalDateTime.now();
//		Schedule s = new Schedule(start);
//		s.add(p1);
//		
//		s.insertAfter(s.getScheduledProgrammes().get(0), null);
//	}

}
