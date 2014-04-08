package us.bleibinha.droolsscalaexample

import java.util.ArrayList
import scala.collection.JavaConversions.iterableAsScalaIterable

object ImmigrationEngine {

  def determineEligibleApplicants(facts: List[ImmigrationFact]): List[Applicant] = {
    val eligibleApplicants = new ArrayList[Applicant]
    val session = Kie.newSession

    session.setGlobal("eligibleApplicants", eligibleApplicants)
    facts map session.insert

    session.fireAllRules()
    session.dispose()

    eligibleApplicants.toList
  }
}
