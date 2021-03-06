import scala.quoted._

object Macro {
  inline def foo: String = ${ fooImpl }
  def fooImpl(using qctx: QuoteContext) : Expr[String] = {
    import qctx.reflect._
    val list = List(
      Symbol.requiredPackage("java"),
      Symbol.requiredPackage("java.lang"),
      Symbol.requiredPackage("scala"),
      Symbol.requiredPackage("scala.collection"),

      Symbol.requiredClass("java.lang.Object"),
      Symbol.requiredClass("scala.Any"),
      Symbol.requiredClass("scala.AnyRef"),
      Symbol.requiredClass("scala.AnyVal"),
      Symbol.requiredClass("scala.Unit"),
      Symbol.requiredClass("scala.Null"),

      Symbol.requiredModule("scala.None"),
      Symbol.requiredModule("scala.Nil"),

      Symbol.requiredMethod("scala.List.empty"),
    )
    Expr(list.map(_.fullName).mkString("\n"))
  }
}
