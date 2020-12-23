package api;

import com.google.gson.*;

import java.lang.reflect.Type;
import java.util.HashMap;

/**
 * exibit directed weighted graph from the Json String.
 */
public class Json_Deserial implements JsonDeserializer {
    @Override
    public DWGraph_DS deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
        JsonObject js = jsonElement.getAsJsonObject();
        HashMap<Integer, node_data> node = new HashMap<>();
        DWGraph_DS dgra = new DWGraph_DS(node);
        JsonArray nodes = js.get("Nodes").getAsJsonArray();
        JsonArray eArray = js.get("Edges").getAsJsonArray();
        for (JsonElement n : nodes) {
            String pos = n.getAsJsonObject().get("pos").getAsString();
            String[] s = pos.split(",");
            node.put(n.getAsJsonObject().get("id").getAsInt(), new Node(n.getAsJsonObject().get("id").getAsInt()
                    , new geolocation(Double.parseDouble(s[0]),
                    Double.parseDouble(s[1]),
                    Double.parseDouble(s[2]))));
        }
        for (JsonElement e : eArray) {
            dgra.connect(e.getAsJsonObject().get("src").getAsInt(),
                    e.getAsJsonObject().get("dest").getAsInt(),
                    e.getAsJsonObject().get("w").getAsDouble());
        }
        return dgra;
    }

}
