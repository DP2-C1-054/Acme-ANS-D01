<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form>
	<acme:input-textbox code="airline-manager.flight.form.label.tag" path="tag"/>
	<acme:input-checkbox code="airline-manager.flight.form.label.selfTransfer" path="requiresSelfTransfer"/>
	<acme:input-money code="airline-manager.flight.form.label.cost" path="cost"/>
	<acme:input-textarea code="airline-manager.flight.form.label.description" path="description"/>
	<acme:input-moment code="airline-manager.flight.form.label.scheduledDeparture" path="scheduledDeparture" readonly="true"/>
	<acme:input-moment code="airline-manager.flight.form.label.scheduledArrival" path="scheduledArrival" readonly="true"/>
	<acme:input-textbox code="airline-manager.flight.form.label.layovers" path="layovers" readonly="true"/>
	<acme:input-textbox code="airline-manager.flight.form.label.departure" path="departure" readonly="true"/>
	<acme:input-textbox code="airline-manager.flight.form.label.arrival" path="arrival" readonly="true"/>
	
	<jstl:choose>
	<jstl:when test="${_command == 'show' && draftMode == false}">
		<acme:button code="airline-manager.flight.form.button.legs" action="/airline-manager/leg/list?flightId=${id}" />
		</jstl:when>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete') && draftMode == true}">
			<acme:button code="airline-manager.flight.form.button.legs" action="/airline-manager/leg/list?flightId=${id}" />
			<acme:submit code="airline-manager.flight.form.button.update" action="/airline-manager/flight/update"/>
			<acme:submit code="airline-manager.flight.form.button.delete" action="/airline-manager/flight/delete"/>
			<acme:submit code="airline-manager.flight.form.button.publish" action="/airline-manager/flight/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="airline-manager.flight.form.button.create" action="/airline-manager/flight/create"/>
		</jstl:when>	
	</jstl:choose>
</acme:form>