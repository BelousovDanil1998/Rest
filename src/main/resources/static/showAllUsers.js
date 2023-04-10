const AllUsersID = "AllUsersTable";
const getAllUsers = 'http://localhost:8080/getAllUsers';

showAllUsers();

function showAllUsers() {
    let tBody = document.getElementById(AllUsersID);
    tBody.innerHTML = "";
    fetch(getAllUsers)
        .then(response => response.json())
        .then(users => {
            users.forEach(function (user) {
                let row = tBody.insertRow();
                row.setAttribute("id", user.id);
                let cell0 = row.insertCell();
                cell0.innerHTML = user.id;
                let cell1 = row.insertCell();
                cell1.innerHTML = user.firstName;
                let cell2 = row.insertCell();
                cell2.innerHTML = user.email;
                let cell3 = row.insertCell();
                cell3.innerHTML = listRoles(user).textContent;

                let cell4 = row.insertCell();
                cell4.innerHTML =
                    '<button type="button" onclick="getModalEdit(' + user.id + ')" class="btn btn-primary btn-sm">Edit</button>';

                let cell5 = row.insertCell();
                cell5.innerHTML =
                    '<button type="button" onclick="modalDelete(' + user.id + ')" class="btn btn-danger btn-sm">Delete</button>';
            })
        });
}