<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<a href="#/client/list">ALL CLIENTS</a>|
<h2>New Client</h2>
<span class="error" jv-error-key></span>
<hr />
<table ng-form name="form">
	<tr>
		<td>Name</td>
		<td>
			<input name="name" type="text" ng-model="name" jv-path="name" />
			<span class="error" ng-if="form.name.$error.jvpath" ng-repeat="msg in form.name.$jvlist">{{msg}}</span>
		</td>
	</tr>
	<tr>
		<td>Last name</td>
		<td>
			<input name="lastname" type="text" ng-model="lastname" jv-path="lastname" />
			<span class="error" ng-if="form.lastname.$error.jvpath" ng-repeat="msg in form.lastname.$jvlist">{{msg}}</span>
		</td>
	</tr>
	<tr>
		<td>Age</td>
		<td>
			<input name="age" type="text" ng-model="age" jv-path="age" />
			<span class="error" ng-if="form.age.$error.jvpath" ng-repeat="msg in form.age.$jvlist">{{msg}}</span>
		</td>
	</tr>
	<tr>
		<td>Logins</td>
		<td>
			<table>
				<thead>
					<tr>
						<th>Login</th>
						<th>Password</th>
						<th>Enabled</th>
						<th></th>
					</tr>
				</thead>
				<tbody>
					<tr ng-repeat="row in loginRows" ng-form name="loginForm">
						<td>
							<input name="loginName" type="text" ng-model="row.name" jv-path="{{'logins['+ $index + '].name'}}" />
							<span class="error" ng-if="loginForm.loginName.$error.jvpath" ng-repeat="msg in loginForm.loginName.$jvlist">{{msg}}</span>
						</td>
						<td>
							<input name="password" type="text" ng-model="row.password" jv-path="{{'logins['+ $index + '].password'}}" />
							<span class="error" ng-if="loginForm.password.$error.jvpath" ng-repeat="msg in loginForm.password.$jvlist">{{msg}}</span>
						</td>
						<td>
							<input name="enabled" type="checkbox" ng-model="row.enabled" jv-path="{{'logins['+ $index + '].enabled'}}" />
							<span class="error" ng-if="loginForm.enabled.$error.jvpath" ng-repeat="msg in loginForm.enabled.$jvlist">{{msg}}</span>
						</td>
						<td>
							<input type="button" ng-click="onRemoveLoginClick(row)" value="Delete" />
						</td>
					</tr>
				</tbody>
			</table>
			<div style="padding-bottom: 0.5em;">
				<input type="button" ng-click="onAddLoginClick()" value="Add" />
				<span class="error" jv-error-key="logins"></span>
			</div>
		</td>
	</tr>
</table>
<hr />
<input type="button" ng-click="onSave()" value="Save" />