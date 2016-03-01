import Chapter11._
import org.scalatest.{FlatSpec, Matchers}

class Chapter11Spec extends FlatSpec with Matchers {

  "Task1" should "evaluate expressions according to the precedence rules" in {
    //when & then
    (3 + 4 -> 5) shouldBe ((3 + 4) -> 5)
    (3 -> 4 + "5") shouldBe ((3 -> 4) + "5")
  }

  "Fraction" should "be normalized and has operations +, -, *, /" in {
    //when & then
    Fraction(15, -6).toString shouldBe "-5/2"
    (Fraction(1, 2) + Fraction(1, 2)).toString shouldBe "1/1"
    (Fraction(1, 2) - Fraction(1, 2)).toString shouldBe "0/1"
    (Fraction(1, 2) * Fraction(1, 2)).toString shouldBe "1/4"
    (Fraction(1, 2) / Fraction(1, 2)).toString shouldBe "1/1"
  }

  "Money" should "be normalized and has operations +, -, ==, <" in {
    //when & then
    Money(1, 150).toString shouldBe "2.50"
    (Money(1, 75) + Money(0, 50)).toString shouldBe "2.25"
    (Money(1, 75) + Money(0, 10)).toString shouldBe "1.85"
    (Money(2, 25) - Money(0, 50)).toString shouldBe "1.75"
    (Money(2, 25) - Money(0, 25)).toString shouldBe "2.00"
    Money(2, 25) == Money(0, 50) shouldBe false
    Money(2, 25) == Money(2, 25) shouldBe true
    Money(2, 25) < Money(2, 25) shouldBe false
    Money(1, 50) < Money(2, 25) shouldBe true
  }

  "Table" should "provide operators that construct an HTML table" in {
    //when
    val table = Table() | "Java" | "Scala" || "Gosling" | "Odersky" || "JVM" | "JVM, .NET"

    //then
    table.toHtml shouldBe "<table><tr><td>Java</td><td>Scala</td></tr>" +
      "<tr><td>Gosling</td><td>Odersky</td></tr>" +
      "<tr><td>JVM</td><td>JVM, .NET</td></tr></table>"
  }

  "ASCIIArt" should "provide operators for combining figures horizontally and vertically" in {
    //given
    val art = new ASCIIArt +
      """ /\_/\""" +
      """( ' ' )""" +
      """(  -  )""" +
      """ | | |""" +
      """(__|__)"""

    //when
    val result = art | new ASCIIArt +
      """    -----""" +
      """  / Hello \""" +
      """ <  Scala |""" +
      """  \ Coder /""" +
      """    -----"""

    //then
    art.toString shouldBe """ /\_/\
                            |( ' ' )
                            |(  -  )
                            | | | |
                            |(__|__)""".stripMargin

    result.toString shouldBe """ /\_/\     -----
                               |( ' ' )  / Hello \
                               |(  -  ) <  Scala |
                               | | | |   \ Coder /
                               |(__|__)    -----""".stripMargin
  }

  "BitSequence" should "store 64 bits, packed in Long" in {
    //given
    val bits = new BitSequence

    //when & then
    bits(0) shouldBe 0
    bits(0) = 1
    bits(0) shouldBe 1
    bits(1) shouldBe 0
    bits(1) = 1
    bits(1) shouldBe 1
    bits(63) shouldBe 0
    bits(63) = 1
    bits(63) shouldBe 1
    bits(0) shouldBe 1
    bits(0) = 0
    bits(0) shouldBe 0
    bits(1) shouldBe 1
    bits(1) = 0
    bits(1) shouldBe 0
    bits(63) shouldBe 1
    bits(63) = 0
    bits(63) shouldBe 0
  }
}
