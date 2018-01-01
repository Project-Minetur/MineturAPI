package de.minetur.mineturapi.profile;

import com.google.gson.TypeAdapter;
/*
Warnung:
Dieser UUIDFetcher wurde von GitHub heruntergeladen.
*/
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import java.util.UUID;

public class UUIDType extends TypeAdapter<UUID> {
	public UUIDType() {
	}

	public void write(JsonWriter out, UUID value) throws IOException {
		out.value(fromUUID(value));
	}

	public UUID read(JsonReader in) throws IOException {
		return fromString(in.nextString());
	}

	public static String fromUUID(UUID value) {
		return value.toString().replace("-", "");
	}

	public static UUID fromString(String input) {
		return UUID.fromString(input.replaceFirst("(\\w{8})(\\w{4})(\\w{4})(\\w{4})(\\w{12})", "$1-$2-$3-$4-$5"));
	}
}
