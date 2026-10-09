package net.mcreator.zingsbirdzing.procedures;

import java.util.Calendar;

public class HalloweenBirdzingDisplayConditionProcedure {
	public static boolean execute() {
		return Calendar.getInstance().get(Calendar.MONTH) == 10;
	}
}