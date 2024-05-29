<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit User</title>
        <!-- Bootstrap CSS -->
        <link href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
        <!-- Custom CSS -->
        <link href="css/style.css" rel="stylesheet">
    </head>
    <body>
        <div class="container mt-5">
            <h1 class="text-center">Edit User</h1>
            <h2 class="text-center mt-3">Update with ID: ${s.ID}</h2>
            <div class="row justify-content-center mt-3">
                <div class="col-md-8">
                    <form action="edituser" method="post">
                        <div class="form-group">
                            <label for="userID">USER ID</label>
                            <input type="text" class="form-control" id="userID" value="${s.ID}" name="ID" readonly>
                        </div>
                        <div class="form-group">
                            <label for="fullname">FULL NAME</label>
                            <input type="text" class="form-control" id="fullname" value="${s.fullname}" name="fullname" required>
                        </div>
                        <div class="form-group">
                            <label for="email">EMAIL</label>
                            <input type="email" class="form-control" id="email" value="${s.email}" name="email" required>
                        </div>
                        <div class="form-group">
                            <label for="dateOfBirth">DATE OF BIRTH</label>
                            <input type="date" class="form-control" id="dateOfBirth" value="${s.dateOfBirth}" name="dateOfBirth" required>
                        </div>
                        <div class="form-group">
                            <label>ROLE</label>
                            <input type="text" class="form-control" value="${s.role}" name="role">
                        </div>
                        <div class="form-group">
                            <label>STATUS</label>
                            <input type="text" class="form-control" value="${s.status}" name="status">
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Update</button>
                        </div>
                    </form>
                </div>
            </div>
            <h2 class="text-center text-danger mt-3">${requestScope.err}</h2>
        </div>
        <!-- Bootstrap JS -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
