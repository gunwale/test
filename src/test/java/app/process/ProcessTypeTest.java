package app.process;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ProcessTypeTest {

  static final String NOTEPAD = "NOTEPAD";
  static final String CMD = "CMD";
  static final String MSPAINT = "MSPAINT";
  static final String CALC = "CALC";

  @Test
  @DisplayName("Test process type")
  void testProcessType() {
    assertThat(ProcessType.get(NOTEPAD)).isEqualTo(ProcessType.NOTEPAD);
    assertThat(ProcessType.get(CMD)).isEqualTo(ProcessType.CMD);
    assertThat(ProcessType.get(MSPAINT)).isEqualTo(ProcessType.MSPAINT);
  }

  @Test
  @DisplayName("Test process type with calc")
  void testProcessTypeWithCalc() {
    assertThatThrownBy(() -> ProcessType.get(CALC)).isInstanceOf(IllegalArgumentException.class)
        .hasMessageContaining("No enum constant app.process.ProcessType.CALC");
  }
}