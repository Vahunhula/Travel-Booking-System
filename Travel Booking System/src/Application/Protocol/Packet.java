package Application.Protocol;

import com.google.gson.Gson;

public class Packet {
    private MenuOptions option;
    private Object data;

    private Throwable exception;

    public Packet(MenuOptions option, Object data) {
        this.option = option;
        this.data = data;
    }

    //constructor to handle exceptions
    public Packet(Throwable exception, Object data) {
        this.exception = exception;
        this.data = data;
    }

    public Packet(Object data) {
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

