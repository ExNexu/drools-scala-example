package us.bleibinha.droolsscalaexample

/**
 * Created by stefan on 2014-04-08.
 */
object RunTest {
  def main(args: Array[String]) = {
    val eligibleApplicant = Applicant(18, "Max")
    val inEligibleApplicant = Applicant(18, "Michael")
    val experience = Experience(eligibleApplicant, 5, "Software Engineering")
    val immigrationFacts = eligibleApplicant :: inEligibleApplicant :: experience :: Nil
    val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
    println(eligibleApplicants)
  }
}
