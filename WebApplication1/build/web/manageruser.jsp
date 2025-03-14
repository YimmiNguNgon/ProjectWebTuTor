<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>User Management</title>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Varela+Round|Open+Sans">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/icon?family=Material+Icons">

        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>

        <style>
            body {
                font-family: 'Open Sans', sans-serif;
                background: #F5F7FA;
                color: #404E67;
            }
            .table-wrapper {
                width: 90%;
                margin: 30px auto;
                background: #fff;
                padding: 20px;
                box-shadow: 0 1px 1px rgba(0,0,0,.05);
            }
            .table-title h2 {
                margin: 6px 0;
                font-size: 22px;
            }
            .table-title .add-new {
                float: right;
                font-size: 14px;
                min-width: 120px;
                border-radius: 50px;
            }
            table.table th, table.table td {
                border-color: #e9e9e9;
                padding: 12px;
                text-align: center;
            }
            table.table th {
                background: #f8f9fa;
            }
            table.table td {
                word-wrap: break-word;
                max-width: 200px;
            }
        </style>
    </head>
    <body>
        <div class="container-lg">
            <div class="table-responsive">
                <div class="table-wrapper">
                    <div class="table-title">
                        <div class="row">
                            <div class="col-sm-8"><h2>User <b>Details</b></h2></div>
                            <div class="col-sm-4">
                                <!--                                <button type="button" class="btn btn-info add-new"><i class="fa fa-plus"></i> Add New</button>-->
                                <a href="#addEmployeeModal" class="btn btn-success" data-toggle="modal"><i class="material-icons">&#xE147;</i> <span>Add New Account</span></a>
                            </div>

                        </div>
                        <br>
                    </div>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th style="width: 10%;">User ID</th>
                                <th style="width: 10%;">Role</th>
                                <th style="width: 15%;">Name</th>
                                <th style="width: 20%;">Email</th>
                                <th style="width: 20%;">Password</th>
                                <th style="width: 15%;">Phone</th>
                                <th style="width: 10%;">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${ListA}" var="o">
                                <tr>
                                    <td>${o.userId}</td>
                                    <td>${o.roleId}</td>
                                    <td>${o.name}</td>
                                    <td>${o.email}</td>
                                    <td>${o.password}</td>
                                    <td>${o.phone}</td>
                                    <td>
                                        <a href="loadaccount?uid=${o.userId}" class="edit"><i class="material-icons" data-toggle="tooltip" title="Edit">&#xE254;</i></a>
                                        <a href="deleteaccount?uid=${o.userId}" class="delete"><i class="material-icons" data-toggle="tooltip" title="Delete" >&#xE872;</i></a>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
    <script>
        $(document).ready(function () {
            $('[data-toggle="tooltip"]').tooltip(); // Kích hoạt tooltip
        });
    </script>

    <div id="addEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="addaccount" method="POST">
                    <div class="modal-header">						
                        <h4 class="modal-title">Add Account</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <!--                        <div class="form-group">
                                                    <label>User ID</label>
                                                    <input name="userid" type="text" class="form-control" required>
                                                </div>-->
                        <div class="form-group">
                            <label>Role</label>
                            <input name="role" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input name="name" type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input name="email" type="email" class="form-control" required>
                        </div>					
                        <div class="form-group">
                            <label>Password</label>
                            <input name="pass" type="text" class="form-control" required>
                        </div>					
                        <div class="form-group">
                            <label>Phone</label>
                            <input name="phone" type="text" class="form-control" required>
                        </div>					
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-success" value="Add">
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Edit Modal HTML -->

    <div id="editEmployeeModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <form>
                    <div class="modal-header">						
                        <h4 class="modal-title">Edit Employee</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                    </div>
                    <div class="modal-body">					
                        <div class="form-group">
                            <label>User ID</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Role</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Name</label>
                            <input type="text" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Email</label>
                            <input type="email" class="form-control" required>
                        </div>
                        <div class="form-group">
                            <label>Password</label>
                            <textarea class="form-control" required></textarea>
                        </div>
                        <div class="form-group">
                            <label>Phone</label>
                            <input type="text" class="form-control" required>
                        </div>					
                    </div>
                    <div class="modal-footer">
                        <input type="button" class="btn btn-default" data-dismiss="modal" value="Cancel">
                        <input type="submit" class="btn btn-info" value="Save">
                    </div>
                </form>
            </div>
        </div>
    </div>
</html>

