package app.command;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.assertj.core.api.ThrowableAssert.ThrowingCallable;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import app.process.ProcessType;

public class CommandTest {

  static final String PROCESS = "NOTEPAD";

  static final String PARAM1 = "ABC";
  static final String PARAM2 = "123";

  static final String[] INPUT_ARRAY = new String[] { PROCESS, PARAM1, PARAM2 };
  static final String[] EMPTY_ARRAY = new String[] {};

  static final String[] PROCESS_ONLY = new String[] { PROCESS };
  static final String[] PARAMETERS_ONLY = new String[] { PARAM1, PARAM2 };

  @Test
  @DisplayName("Test parse processType")
  void testParseProcessType() {
    ProcessType processType = Command.parseProcessType(INPUT_ARRAY);
    assertThat(processType).isEqualTo(ProcessType.NOTEPAD);
  }

  @Test
  @DisplayName("Test parse processType with null")
  void testParseProcessTypeWithNull() {
    hasNullPointerException(() -> Command.parseProcessType(null));
  }

  @Test
  @DisplayName("Test parse processType with empty")
  void testParseProcessTypeWithEmpty() {
    hasArrayIndexOutOfBoundsException(() -> Command.parseProcessType(EMPTY_ARRAY));
  }

  @Test
  @DisplayName("Test parse parameters")
  void testParseParameters() {

    String[] parameters = Command.parseParameters(INPUT_ARRAY);
    assertThat(parameters).isEqualTo(CommandTest.PARAMETERS_ONLY);
  }

  @Test
  @DisplayName("Test parse parameters with null")
  void testParseParametersWithNull() {
    hasNullPointerException(() -> Command.parseParameters(null));
  }

  @Test
  @DisplayName("Test parse parameters with empty")
  void testParseParametersWithEmpty() {
    assertThat(Command.parseParameters(EMPTY_ARRAY)).isEqualTo(EMPTY_ARRAY);
  }

  @Test
  @DisplayName("Test parse command")
  void testParseCommand() {

    Command command = Command.parse(INPUT_ARRAY);
    assertThat(command).isNotNull();
    assertThat(command.getProcessType()).isNotNull().isEqualTo(ProcessType.NOTEPAD);
    assertThat(command.getParameters()).isNotNull().isEqualTo(PARAMETERS_ONLY);
  }

  @Test
  @DisplayName("Test parse command with null")
  void testParseCommandWithNull() {
    hasNullPointerException(() -> Command.parse(null));
  }

  @Test
  @DisplayName("Test parse command with empty")
  void testParseCommandWithEmpty() {
    hasArrayIndexOutOfBoundsException(() -> Command.parse(EMPTY_ARRAY));
  }

  @Test
  @DisplayName("Test parse command with process only")
  void testParseCommandWithProcessOnly() {
    Command command = Command.parse(PROCESS_ONLY);

    assertThat(command).isNotNull();
    assertThat(command.getProcessType()).isNotNull().isEqualTo(ProcessType.NOTEPAD);
    assertThat(command.getParameters()).isNotNull().isEqualTo(EMPTY_ARRAY);
  }

  static void hasNullPointerException(ThrowingCallable throwingCallable) {
    assertThatThrownBy(throwingCallable).isInstanceOf(NullPointerException.class)
        .hasMessageContaining("args is marked non-null but is null");
  }

  static void hasArrayIndexOutOfBoundsException(ThrowingCallable throwingCallable) {
    assertThatThrownBy(throwingCallable).isInstanceOf(ArrayIndexOutOfBoundsException.class).hasMessageContaining("0");
  }

}
