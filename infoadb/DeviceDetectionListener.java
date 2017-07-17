package infoadb;

import java.util.List;

public interface DeviceDetectionListener {
    public void onDetect(List<JadbDevice> devices);
    public void onException(Exception e);
}

