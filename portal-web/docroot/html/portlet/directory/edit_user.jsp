<%
/**
 * Copyright (c) 2000-2008 Liferay, Inc. All rights reserved.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
%>

<%@ include file="/html/portlet/directory/init.jsp" %>

<%
String tabs2 = ParamUtil.getString(request, "tabs2", "display");
String tabs3 = ParamUtil.getString(request, "tabs3", "email-addresses");
String tabs4 = ParamUtil.getString(request, "tabs4", "phone-numbers");

String redirect = ParamUtil.getString(request, "redirect");
String backURL = ParamUtil.getString(request, "backURL", redirect);

User user2 = PortalUtil.getSelectedUser(request);

boolean editable = false;

Contact contact2 = null;

if (user2 != null) {
	contact2 = user2.getContact();
}

PasswordPolicy passwordPolicy = null;

if (user2 == null) {
	passwordPolicy = PasswordPolicyLocalServiceUtil.getDefaultPasswordPolicy(company.getCompanyId());
}
else {
	passwordPolicy = user2.getPasswordPolicy();
}

String emailAddress = BeanParamUtil.getString(user2, request, "emailAddress");

request.setAttribute("edit_user.jsp-user2", user2);
%>

<script type="text/javascript">
	function <portlet:namespace />saveUser(cmd) {
		document.<portlet:namespace />fm.<portlet:namespace /><%= Constants.CMD %>.value = cmd;

		var redirect = "<portlet:renderURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/directory/edit_user" /></portlet:renderURL>";

		if (document.<portlet:namespace />fm.<portlet:namespace />tabs2) {
			redirect += "&<portlet:namespace />tabs2=" + document.<portlet:namespace />fm.<portlet:namespace />tabs2.value;
		}

		if (document.<portlet:namespace />fm.<portlet:namespace />tabs3) {
			redirect += "&<portlet:namespace />tabs3=" + document.<portlet:namespace />fm.<portlet:namespace />tabs3.value;
		}

		if (document.<portlet:namespace />fm.<portlet:namespace />tabs4) {
			redirect += "&<portlet:namespace />tabs4=" + document.<portlet:namespace />fm.<portlet:namespace />tabs4.value;
		}

		redirect += "&<portlet:namespace />backURL=<%= HttpUtil.encodeURL(backURL) %>&<portlet:namespace />p_u_i_d=";

		document.<portlet:namespace />fm.<portlet:namespace />redirect.value = redirect;
		submitForm(document.<portlet:namespace />fm, "<portlet:actionURL windowState="<%= WindowState.MAXIMIZED.toString() %>"><portlet:param name="struts_action" value="/directory/edit_user" /></portlet:actionURL>");
	}
</script>

<form method="post" name="<portlet:namespace />fm">
<input name="<portlet:namespace /><%= Constants.CMD %>" type="hidden" value="" />
<input name="<portlet:namespace />tabs2" type="hidden" value="<%= HtmlUtil.escape(tabs2) %>" />
<input name="<portlet:namespace />tabs3" type="hidden" value="<%= HtmlUtil.escape(tabs3) %>" />
<input name="<portlet:namespace />tabs4" type="hidden" value="<%= HtmlUtil.escape(tabs4) %>" />
<input name="<portlet:namespace />redirect" type="hidden" value="" />
<input name="<portlet:namespace />backURL" type="hidden" value="<%= HtmlUtil.escape(backURL) %>" />
<input name="<portlet:namespace />p_u_i_d" type="hidden" value='<%= (user2 != null) ? user2.getUserId() : 0 %>' />

<liferay-ui:tabs
	names="user"
	backURL="<%= backURL %>"
/>

<%@ include file="/html/portlet/directory/edit_user_profile.jspf" %>

<c:if test="<%= user2 != null %>">
	<c:if test="<%= (passwordPolicy != null) && user2.getLockout() %>">
		<liferay-ui:tabs names="lockout" />

		<%--<%@ include file="/html/portlet/enterprise_admin/edit_user_lockout.jspf" %>--%>
	</c:if>

	<liferay-ui:tabs
		names="email-addresses,addresses,websites"
		formName="fm"
		param="tabs3"
		refresh="<%= false %>"
	>
		<liferay-ui:section>
			<liferay-util:include page="/html/portlet/enterprise_admin/email_address_iterator.jsp">
				<liferay-util:param name="editable" value="<%= String.valueOf(editable) %>" />
				<liferay-util:param name="redirect" value="<%= currentURL + sectionRedirectParams %>" />
				<liferay-util:param name="className" value="<%= Contact.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(contact2.getContactId()) %>" />
			</liferay-util:include>
		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:include page="/html/portlet/enterprise_admin/address_iterator.jsp">
				<liferay-util:param name="editable" value="<%= String.valueOf(editable) %>" />
				<liferay-util:param name="redirect" value="<%= currentURL + sectionRedirectParams %>" />
				<liferay-util:param name="className" value="<%= Contact.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(contact2.getContactId()) %>" />
				<liferay-util:param name="organizationIds" value="<%= StringUtil.merge(organizationIdsArray) %>" />
			</liferay-util:include>
		</liferay-ui:section>
		<liferay-ui:section>
			<liferay-util:include page="/html/portlet/enterprise_admin/website_iterator.jsp">
				<liferay-util:param name="editable" value="<%= String.valueOf(editable) %>" />
				<liferay-util:param name="redirect" value="<%= currentURL + sectionRedirectParams %>" />
				<liferay-util:param name="className" value="<%= Contact.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(contact2.getContactId()) %>" />
			</liferay-util:include>
		</liferay-ui:section>
	</liferay-ui:tabs>

	<liferay-ui:tabs
		names="phone-numbers,sms-messenger-id,instant-messenger-ids,social-network-ids"
		formName="fm"
		param="tabs4"
		refresh="<%= false %>"
	>
		<liferay-ui:section>
			<liferay-util:include page="/html/portlet/enterprise_admin/phone_iterator.jsp">
				<liferay-util:param name="editable" value="<%= String.valueOf(editable) %>" />
				<liferay-util:param name="redirect" value="<%= currentURL + sectionRedirectParams %>" />
				<liferay-util:param name="className" value="<%= Contact.class.getName() %>" />
				<liferay-util:param name="classPK" value="<%= String.valueOf(contact2.getContactId()) %>" />
				<liferay-util:param name="organizationIds" value="<%= StringUtil.merge(organizationIdsArray) %>" />
			</liferay-util:include>
		</liferay-ui:section>
		<liferay-ui:section>
			<%--<%@ include file="/html/portlet/enterprise_admin/edit_user_sms.jspf" %>--%>
		</liferay-ui:section>
		<liferay-ui:section>
			<%--<%@ include file="/html/portlet/enterprise_admin/edit_user_im.jspf" %>--%>
		</liferay-ui:section>
		<liferay-ui:section>
			<%--<%@ include file="/html/portlet/enterprise_admin/edit_user_social.jspf" %>--%>
		</liferay-ui:section>
	</liferay-ui:tabs>

	<%--<%@ include file="/html/portlet/enterprise_admin/edit_user_comments.jspf" %>--%>

	<%
	PortalUtil.setPageSubtitle(user2.getFullName(), request);
	%>

</c:if>

</form>