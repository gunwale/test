package app.process;

public enum ProcessType {
  CMD, NOTEPAD, MSPAINT;

  public static ProcessType get(String processType) {
    return ProcessType.valueOf(processType);
  }
}
