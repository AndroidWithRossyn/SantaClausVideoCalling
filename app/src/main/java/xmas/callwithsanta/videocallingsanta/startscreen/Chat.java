package xmas.callwithsanta.videocallingsanta.startscreen;

public class Chat {
    String message;
    int type;

    public Chat(int i, String str) {
        this.type = i;
        this.message = str;
    }

    public int getType() {
        return this.type;
    }

    public void setType(int i) {
        this.type = i;
    }

    public String getMessage() {
        return this.message;
    }

    public void setMessage(String str) {
        this.message = str;
    }
}
