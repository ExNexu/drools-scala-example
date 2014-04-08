package us.bleibinha.droolsscalaexample

import org.kie.api.KieServices
import org.kie.api.runtime.{StatelessKieSession, KieContainer}

object Kie {
  private lazy val kieServices: KieServices = KieServices.Factory.get()
  private lazy val kContainer: KieContainer = kieServices.getKieClasspathContainer()

  lazy val statelessSession: StatelessKieSession = kContainer.newStatelessKieSession()

  def execute(facts: List[Any]) = statelessSession.execute(facts)

  def session = kContainer.newKieSession()
}
