package us.bleibinha.droolsscalaexample

import java.io.{FileInputStream, InputStreamReader}
import org.drools.compiler.PackageBuilder
import org.drools.RuleBaseFactory
import org.drools.audit.WorkingMemoryFileLogger

object HelloWorld {
    def main(args: Array[String]) {
      usageCase1(Nil)
    }

    def usageCase1(facts: List[Any]) {
      System.setProperty("drools.dialect.mvel.strict", "false")

      val rulesfilename = "src/main/resources/us/bleibinha/droolsscalaexample/Example1.drl"
      val source = new InputStreamReader(new FileInputStream(rulesfilename))

      val builder = new PackageBuilder()
      builder.addPackageFromDrl(source)
      if (builder.hasErrors()) {
        System.out.println(builder.getErrors().toString())
        throw new RuntimeException("Unable to compile " + rulesfilename + ".")
      }

      val pkg = builder.getPackage()
      val ruleBase = RuleBaseFactory.newRuleBase()
      ruleBase.addPackage(pkg)

      val session = ruleBase.newStatefulSession()
      //session.addEventListener(new org.drools.event.DebugAgendaEventListener())
      //session.addEventListener(new org.drools.event.DebugWorkingMemoryEventListener())

      // setup the audit logging
//      val logger:WorkingMemoryFileLogger = new WorkingMemoryFileLogger(session)
//      logger.setFileName("drools")
//      logger.writeToDisk()

      facts.foreach(session.insert(_))

      session.fireAllRules()

      session.dispose()
    }
}

