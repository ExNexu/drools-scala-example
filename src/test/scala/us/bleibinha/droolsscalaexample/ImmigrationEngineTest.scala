package us.bleibinha.droolsscalaexample

import org.scalatest.{ShouldMatchers, FunSpec}

class ImmigrationEngineTest extends FunSpec with ShouldMatchers{

  describe("ImmigrationEngine") {

    describe("Single adults") {

      it("should be eligible with at least 5 years of experience") {
        val eligibleApplicant = Applicant(18, "Max")
        val inEligibleApplicant = Applicant(18, "Michael")
        val experience = Experience(eligibleApplicant, 5, "Software engineering")
        val immigrationFacts = eligibleApplicant :: inEligibleApplicant :: experience :: Nil
        val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
        eligibleApplicants should contain (eligibleApplicant)
        eligibleApplicants should not contain (inEligibleApplicant)
      }

    }

  }

}

