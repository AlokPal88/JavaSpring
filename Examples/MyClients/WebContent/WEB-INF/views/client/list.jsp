<a href="#/client/create">NEW CLIENT</a>&nbsp;|
<h2>Clients</h2>
<span class="error" jv-error-key></span>
<hr />
<table border="1" style="width: 50%;">
<thead>
	<tr class="head">
		<th>ID</th>
		<th>NAME</th>
		<th>LASTNAME</th>
		<th>AGE</th>
		<th>LOGINS</th>
		<th></th>
		<th></th>
	</tr>
</thead>
<tbody>
	<tr ng-repeat="row in clientRows">
		<td ng-bind="row.id|idtext"></td>
		<td ng-bind="row.name"></td>
		<td ng-bind="row.lastname"></td>
		<td ng-bind="row.age"></td>
		<td>
			<div ng-repeat="login in row.logins">
				<span ng-bind="login.name"></span><br />
			</div>
		</td>
		<td>
			<a ng-href="#/client/edit/{{row.id}}">Edit</a>
		</td>
		<td>
			<input type="button" ng-click="onDelete(row.id)" value="Delete" />
		</td>
	</tr>
</tbody>
</table>