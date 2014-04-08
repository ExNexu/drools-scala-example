package us.bleibinha.droolsscalaexample

import org.scalatest.{ShouldMatchers, FunSpec}

class ImmigrationEngineTest extends FunSpec with ShouldMatchers {

  describe("ImmigrationEngine") {

    describe("Single adults") {

      it("should be eligible with 5 years of experience") {
        val eligibleApplicant = Applicant(18, "Max")
        val inEligibleApplicant = Applicant(18, "Michael")
        val experience = Experience(eligibleApplicant, 5, "Software Engineering")
        val immigrationFacts = eligibleApplicant :: inEligibleApplicant :: experience :: Nil
        val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
        eligibleApplicants should contain(eligibleApplicant)
        eligibleApplicants should not contain (inEligibleApplicant)
      }

      it("should be eligible with a bachelor and 2 years of experience in the same field") {
        val eligibleApplicant = Applicant(18, "Max")
        val inEligibleApplicant1 = Applicant(18, "Michael")
        val inEligibleApplicant2 = Applicant(18, "Sven")
        val applicants =
          List(
            eligibleApplicant,
            inEligibleApplicant1,
            inEligibleApplicant2
          )
        val qualifications =
          List(
            Qualification(eligibleApplicant, BachelorQualification, "Software Engineering"),
            Qualification(inEligibleApplicant2, BachelorQualification, "Software Engineering")
          )
        val experiences =
          List(
            Experience(eligibleApplicant, 2, "Software Engineering"),
            Experience(inEligibleApplicant2, 2, "Waitressing")
          )
        val immigrationFacts = applicants ++ qualifications ++ experiences
        val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
        eligibleApplicants should contain(eligibleApplicant)
        eligibleApplicants should contain noneOf(inEligibleApplicant1, inEligibleApplicant2)
      }

      it("should be eligible with a master") {
        val eligibleApplicant = Applicant(18, "Max")
        val qualification = Qualification(eligibleApplicant, MasterQualification, "Software Engineering")
        val immigrationFacts = eligibleApplicant :: qualification :: Nil
        val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
        eligibleApplicants should contain(eligibleApplicant)
      }

      it("should be eligible with a phd") {
        val eligibleApplicant = Applicant(18, "Max")
        val qualification = Qualification(eligibleApplicant, PhdQualification, "Software Engineering")
        val immigrationFacts = eligibleApplicant :: qualification :: Nil
        val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
        eligibleApplicants should contain(eligibleApplicant)
      }

    }

    describe("Relatives") {

      describe("Children") {

        it("should be eligible if related to an eligible applicant") {
          val applicants = Family(
            List(
              Applicant(5, "Max"),
              Applicant(18, "Maria"),
              Applicant(7, "Sven")
            )
          )
          val eligibleApplicant = applicants.filter(_.name == "Maria")(0)
          val qualification = Qualification(eligibleApplicant, BachelorQualification, "Software Engineering")
          val experience = Experience(eligibleApplicant, 2, "Software Engineering")
          val immigrationFacts = qualification :: experience :: applicants
          val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
          applicants map {
            eligibleApplicants should contain(_)
          }
        }

      }

      describe("Adult relatives") {

        it("should be eligible if related to an applicant with master and two years of experience") {
          val applicants = Family(
            List(
              Applicant(18, "Max"),
              Applicant(18, "Maria"),
              Applicant(78, "Sven")
            )
          )
          val eligibleApplicant = applicants.filter(_.name == "Maria")(0)
          val qualification = Qualification(eligibleApplicant, MasterQualification, "Software Engineering")
          val experience = Experience(eligibleApplicant, 2, "Software Engineering")
          val immigrationFacts = qualification :: experience :: applicants
          val eligibleApplicants = ImmigrationEngine.determineEligibleApplicants(immigrationFacts)
          applicants map {
            eligibleApplicants should contain(_)
          }
        }

      }

    }

  }

}

