package bridge.ui;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public enum Serialization {

    CONSOLE;

    public static InputStream setConsole(String readLine){
        return new ByteArrayInputStream(readLine.getBytes());
    }
}
