package com.massivecraft.creativegates;

import com.massivecraft.creativegates.entity.UGate;

import java.util.Comparator;

public class ExitComparator implements Comparator<UGate> {
	// -------------------------------------------- //
	// INSTANCE & CONSTRUCT
	// -------------------------------------------- //

	private static ExitComparator i = new ExitComparator();

	public static ExitComparator get() {
		return i;
	}

	// -------------------------------------------- //
	// OVERRIDE
	// -------------------------------------------- //

	@Override
	public int compare(UGate o1, UGate o2) {
		if (o1.getType().equals("random")) {
			return o1.getExit().compareTo(o1.getExit());
		}
		return o1.getExit().compareTo(o2.getExit());
	}

}