package us.bleibinha.droolsscalaexample

import scala.beans.BeanInfo

sealed trait ImmigrationFact

@BeanInfo
case class Applicant(age: Int, name: String, relatives: List[Applicant] = Nil) extends ImmigrationFact

object Family {
  def apply(applicants: Seq[Applicant]): Seq[Applicant] =
    applicants map { applicant ⇒
      val relatives = applicants.filterNot(_ == applicant).toList
      applicant.copy(relatives = relatives)
    }
}

@BeanInfo
case class Experience(applicant: Applicant, years: Int, field: String) extends ImmigrationFact

sealed abstract class QualificationLevel(val displayName: String, val numeric: Int) extends ImmigrationFact
case object BasicQualification extends QualificationLevel("Basic qualification", 0)
case object BachelorQualification extends QualificationLevel("Bachelor", 1)
case object MasterQualification extends QualificationLevel("Master", 2)
case object PhdQualification extends QualificationLevel("Phd", 3)

@BeanInfo
case class Qualification(applicant: Applicant, level: QualificationLevel, field: String) extends ImmigrationFact
