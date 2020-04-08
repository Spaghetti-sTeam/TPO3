/**
 *
 *  @author Wierzchałek Karolina S18705
 *
 */

package zad1;


import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

public class Time {
	public static String passed(String from, String to)
	{
		String wiadomość = " ";
		String test1 = "";
		String test2 = "";
		String dpatt = "d MMMM yyyy (EEEE)";
		String tpatt = "d MMMM yyyy (EEEE) 'godz.' HH:mm";

		StringBuilder sb = new StringBuilder();

		LocalDate localDateFrom = null;
		LocalDate localDateTo = null;

		LocalDateTime localDateTimeFrom = null;
		LocalDateTime localDateTimeTo = null;

		DateTimeFormatter dateTimeFormatterD = DateTimeFormatter.ofPattern(dpatt);
		DateTimeFormatter dateTimeFormatterT = DateTimeFormatter.ofPattern(tpatt);

		//ZonedDateTime zonedDateTime = ZonedDateTime.of(dateTimeFormatterT, ZoneId.of("Europe/Warsaw"));

		//String years;
		//String months;
		//String days;

		Locale none = new Locale("xx");
		String tygodnie;
		String dniRoku;
		String godzin = "";
		String minut = "";

		Period period = null;

		try
		{
			if(!from.contains("T") && !to.contains("T"))
			{
				localDateFrom = LocalDate.parse(from);
				localDateTo = LocalDate.parse(to);
				test1 = localDateFrom.format(dateTimeFormatterD);
				test2 = localDateTo.format(dateTimeFormatterD);
				period = Period.between(localDateFrom, localDateTo);

				//years = String.valueOf(ChronoUnit.YEARS.between(localDateFrom, localDateTo));
				//months = String.valueOf(ChronoUnit.MONTHS.between(localDateFrom, localDateTo));
				//days = String.valueOf(ChronoUnit.DAYS.between(localDateFrom, localDateTo));

				dniRoku = String.valueOf(ChronoUnit.DAYS.between(localDateFrom, localDateTo));

				tygodnie = String.format(none, "%.2f", ChronoUnit.DAYS.between(localDateFrom, localDateTo) / 7.0);
			}
			else if(from.contains("T") && to.contains("T"))
			{
				String elments[] = from.split("T");
				LocalDate element0 = LocalDate.parse(elments[0]);
				//Duration duration1 = Duration.parse(elments[1]);

				String element[] = to.split("T");
				LocalDate element0To = LocalDate.parse(element[0]);
				//Duration duration1To = Duration.parse(element[1]);

				localDateTimeFrom = LocalDateTime.parse(from);
				localDateTimeTo = LocalDateTime.parse(to);

				ZonedDateTime zonedDateTimeFrom = ZonedDateTime.of(localDateTimeFrom, ZoneId.of("Europe/Warsaw"));
				ZonedDateTime zonedDateTimeTo = ZonedDateTime.of(localDateTimeTo, ZoneId.of("Europe/Warsaw"));

				test1 = localDateTimeFrom.format(dateTimeFormatterT);
				test2 = localDateTimeTo.format(dateTimeFormatterT);
				period = Period.between(element0, element0To);  // ??

				//years = String.valueOf(ChronoUnit.YEARS.between(localDateTimeFrom, localDateTimeTo));
				//months = String.valueOf(ChronoUnit.MONTHS.between(localDateTimeFrom, localDateTimeTo));
				//days = String.valueOf(ChronoUnit.DAYS.between(localDateTimeFrom, localDateTimeTo));

				dniRoku = String.valueOf(ChronoUnit.DAYS.between(zonedDateTimeFrom, zonedDateTimeTo)); //localDateTimeFrom, localDateTimeTo

				godzin = String.valueOf(ChronoUnit.HOURS.between(zonedDateTimeFrom, zonedDateTimeTo));
				minut = String.valueOf(ChronoUnit.MINUTES.between(zonedDateTimeFrom, zonedDateTimeTo));

				tygodnie = String.format(none, "%.2f", ChronoUnit.DAYS.between(zonedDateTimeFrom, zonedDateTimeTo) / 7.0); //localDateTimeFrom, localDateTimeTo
			}
			else if(!from.contains("T") && to.contains("T"))
			{
				String elments[] = from.split("T");
				LocalDate element0 = LocalDate.parse(elments[0]);
				//Duration duration1 = Duration.parse(elments[1]);

				localDateFrom = LocalDate.parse(from);
				localDateTimeTo = LocalDateTime.parse(to);
				test1 = localDateFrom.format(dateTimeFormatterD);
				test2 = localDateTimeTo.format(dateTimeFormatterT);
				//period = Period.between(localDateFrom, LocalDate.from(localDateTimeTo));  //??

				//years = String.valueOf(ChronoUnit.YEARS.between(localDateFrom, localDateTimeTo));
				//months = String.valueOf(ChronoUnit.MONTHS.between(localDateFrom, localDateTimeTo));
				//days = String.valueOf(ChronoUnit.DAYS.between(localDateFrom, localDateTimeTo));

				dniRoku = String.valueOf(ChronoUnit.DAYS.between(localDateFrom, localDateTimeTo));

				tygodnie = String.format(none, "%.2f", ChronoUnit.DAYS.between(localDateFrom, localDateTimeTo) / 7.0);
			} else // if(from.contains("T") && !to.contains("T"))
			{
				String elments[] = from.split("T");
				LocalDate element0 = LocalDate.parse(elments[0]);
				//Duration duration1 = Duration.parse(elments[1]);

				localDateTimeFrom = LocalDateTime.parse(from);
				localDateTo = LocalDate.parse(to);
				test1 = localDateTimeFrom.format(dateTimeFormatterT);
				test2 = localDateTo.format(dateTimeFormatterD);
				//period = Period.between(LocalDate.from(localDateTimeFrom), localDateTo);  //??

				//years = String.valueOf(ChronoUnit.YEARS.between(localDateTimeFrom, localDateTo));
				//months = String.valueOf(ChronoUnit.MONTHS.between(localDateTimeFrom, localDateTo));
				//days = String.valueOf(ChronoUnit.DAYS.between(localDateTimeFrom, localDateTo));

				dniRoku = String.valueOf(ChronoUnit.DAYS.between(localDateTimeFrom, localDateTo));

				tygodnie = String.format(none, "%.2f", ChronoUnit.DAYS.between(localDateTimeFrom, localDateTo) / 7.0);
			}


			sb.append("Od ").append(test1);
			sb.append(" do ").append(test2);
			sb.append("\n - mija: ");
			if(Integer.parseInt(dniRoku) > 0)
			{
				sb.append(dniRoku);
				{
					if(1 < Integer.parseInt(dniRoku))
					{
						sb.append(" dni, ");
					}
					else
					{
						sb.append(" dzien, ");
					}
				}
			}
			else
			{
				sb.append("0 dni, ");
			}
			if(Double.parseDouble(tygodnie) > 0)
			{
				sb.append("tygodni ");
				sb.append(tygodnie);
			}
			else
			{
				sb.append("0 tygodni ");
			}
			//warunek z T i godzinami
			if(from.contains("T") && to.contains("T"))
			{
				sb.append("\n - godzin: ");
				sb.append(godzin);
				sb.append(", minut: ");
				sb.append(minut);
			}
			sb.append("\n - kalendarzowo: ");
			if(period.getYears() > 0)
			{
				sb.append(period.getYears());
				if(period.getYears() == 1)
				{
					sb.append(" rok");
				}
				else if(period.getYears() > 1 && period.getYears() < 5)
				{
					sb.append(" lata");
				}
				else
				{
					sb.append(" lat");
				}
			}
			if(period.getMonths() > 0)
			{
				sb.append(", " + period.getMonths());
				{
					if(period.getMonths() == 1)
					{
						sb.append(" miesiac");
					}
					else if(period.getMonths() > 1 && period.getMonths() < 5)
					{
						sb.append(" miasiace");
					}
					else
					{
						sb.append(" miesiecy");
					}
				}
			}
			if(period.getDays() > 0)
			{
				if(period.getYears() > 0 && period.getMonths() > 0)
				{
					sb.append(", ");
				}
				sb.append(period.getDays());
				if(period.getDays() == 1)
				{
					sb.append(" dzien ");
				}
				else
				{
					sb.append(" dni ");
				}
			}
		}
		catch(Exception e)
		{
			System.out.print("*** " + e);
		}

		wiadomość = sb.toString();
		return wiadomość;
	}
}
