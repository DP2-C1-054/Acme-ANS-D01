<%@page%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="acme" uri="http://acme-framework.org/"%>

<acme:form> 
	<acme:input-textbox code="customer.booking.form.label.locatorCode" path="locatorCode"/>
	<acme:input-moment code="customer.booking.form.label.purchaseMoment" path="purchaseMoment"/>
	<acme:input-select code="customer.booking.form.label.flight" path="flight" choices="${flights}"/>	
	<acme:input-select code="customer.booking.form.label.travelClass" path="travelClass" choices="${travelClasses}"/>
	<acme:input-integer code="customer.booking.form.label.creditCardNibble" path="creditCardNibble" />
	<acme:button code="customer.booking.form.button.passengers" action="/customer/passenger/list?bookingId=${id}"/>	
	<jstl:choose>
		<jstl:when test="${acme:anyOf(_command, 'show|update|delete|publish') && draftMode == true}">
			<acme:submit code="customer.booking.form.button.update" action="/customer/booking/update"/>
			<acme:submit code="customer.booking.form.button.delete" action="/customer/booking/delete"/>
			<acme:submit code="customer.booking.form.button.publish" action="/customer/booking/publish"/>
		</jstl:when>
		<jstl:when test="${_command == 'create'}">
			<acme:submit code="customer.booking.form.button.create" action="/customer/booking/create?bookingId=${id}"/>
		</jstl:when>		
	</jstl:choose>
</acme:form>