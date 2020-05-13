package app.command;

import java.util.Arrays;

import app.process.ProcessType;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.ToString;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@ToString
public class Command {

  @NonNull
  private ProcessType processType;
  @NonNull
  private String[] parameters;

  private Command(ProcessType processType, String[] parameters) {
    this.processType = processType;
    this.parameters = parameters;
  }

  private static CommandBuilder builder() {
    return new CommandBuilder();
  }

  public static Command parse(String[] args) {
    return Command.builder().processType(parseProcessType(args)).parameters(parseParameters(args)).build();
  }

  static ProcessType parseProcessType(@NonNull String[] args) {
    return ProcessType.get(args[0]);
  }

  static String[] parseParameters(@NonNull String[] args) {
    return Arrays.stream(args).skip(1).toArray(String[]::new);
  }

}
