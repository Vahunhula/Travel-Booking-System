package Application.Protocol;

import com.google.gson.Gson;

public class Packet {
    private MenuOptions option;
    private Object data;

    public Packet(MenuOptions option, Object data) {
        this.option = option;
        this.data = data;
    }

    public MenuOptions getOption() {
        return option;
    }

    public Object getData() {
        return data;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Packet fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Packet.class);
    }
}

