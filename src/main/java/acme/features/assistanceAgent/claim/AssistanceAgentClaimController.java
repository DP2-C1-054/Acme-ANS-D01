
package acme.features.assistanceAgent.claim;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import acme.client.controllers.AbstractGuiController;
import acme.client.controllers.GuiController;
import acme.entities.claims.Claim;
import acme.realms.assistance_agents.AssistanceAgent;

@GuiController
public class AssistanceAgentClaimController extends AbstractGuiController<AssistanceAgent, Claim> {

	@Autowired
	private AssistanceAgentClaimListService			listService;

	@Autowired
	private AssistanceAgentClaimShowService			showService;

	@Autowired
	private AssistanceAgentClaimListPendingService	listPendingService;

	@Autowired
	private AssistanceAgentClaimCreateService		createService;

	@Autowired
	private AssistanceAgentClaimDeleteService		deleteService;

	@Autowired
	private AssistanceAgentClaimUpdateService		updateService;

	@Autowired
	private AssistanceAgentClaimPublishService		publishService;


	@PostConstruct
	protected void initialise() {
		super.addBasicCommand("list", this.listService);
		super.addBasicCommand("show", this.showService);
		super.addBasicCommand("create", this.createService);
		super.addBasicCommand("delete", this.deleteService);
		super.addBasicCommand("update", this.updateService);

		super.addCustomCommand("pending", "list", this.listPendingService);
		super.addCustomCommand("publish", "update", this.publishService);
	}
}
