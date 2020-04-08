/**
 *
 *  @author Wierzchałek Karolina S18705
 *
 */

package zad1;


import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Tools {
	public static Options createOptionsFromYaml(String fileName)
	{
		Yaml yaml = new Yaml();
		List<String> Asia = new ArrayList<>();
		List<String> Adam = new ArrayList<>();
		InputStream inputStream;
		try
		{
			inputStream = new FileInputStream(new File(fileName));
			//System.out.println("Loaded: " + object.getClass());
			//System.out.println(object);
		}
		catch(FileNotFoundException e)
		{
			throw new Error("No such file found");
		}
		Map<String, Object> mapka = yaml.load(inputStream);

		Options o = new Options(mapka.get("host").toString(), Integer.parseInt(mapka.get("port").toString()), Boolean.parseBoolean(mapka.get("concurMode").toString()), Boolean.parseBoolean(mapka.get("showSendRes").toString()), ((LinkedHashMap<String, List<String>>)mapka.get("clientsMap")));
		return o;
	}
}
