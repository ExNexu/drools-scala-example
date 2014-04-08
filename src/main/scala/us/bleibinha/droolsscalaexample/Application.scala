package us.bleibinha.droolsscalaexample

import scala.beans.BeanInfo
import scala.collection.JavaConversions.seqAsJavaList

sealed trait ImmigrationFact

@BeanInfo
case class Applicant(age: Int, name: String, relativeNames: List[String] = Nil) extends ImmigrationFact {
  def relativeNamesJavaList: java.util.List[String] = relativeNames
}

object Family {
  def apply(applicants: List[Applicant]): List[Applicant] =
    applicants map {
      applicant ⇒
        val relativeNames = applicants.filterNot(_ == applicant).map(_.name).toList
        applicant.copy(relativeNames = relativeNames)
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
case class Qualification(applicant: Applicant, level: QualificationLevel, field: String) extends ImmigrationFact {
  def numeric = level.numeric
}
