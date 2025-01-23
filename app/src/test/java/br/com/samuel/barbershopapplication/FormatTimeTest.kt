package br.com.samuel.barbershopapplication

import br.com.samuel.barbershopapplication.utils.formatTime
import junit.framework.TestCase.assertEquals
import org.junit.Test

class FormatTimeTest {
  @Test
  fun formatTimeWithoutSeconds() {
    val time = "14:00:00"
    val timeFormatted = formatTime(time)
    assertEquals("14:00", timeFormatted)
  }
}