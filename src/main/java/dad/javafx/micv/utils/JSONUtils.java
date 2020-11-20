package dad.javafx.micv.utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.FileUtils;
import org.hildan.fxgson.FxGson;

import com.google.gson.Gson;

public class JSONUtils {

	private static final Gson gson = FxGson.fullBuilder().setPrettyPrinting().create();

	public static <T> T fromJson(File file, Class<T> classOfT) throws IOException {
		String json = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
		return gson.fromJson(json, classOfT);
	}
	
	public static void toJson(File file, Object o) throws IOException {
		String json = gson.toJson(o);
		FileUtils.writeStringToFile(file, json, StandardCharsets.UTF_8);
	}

}
