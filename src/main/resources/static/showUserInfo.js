const getAuthorizedUser = 'http://localhost:8080/getAuthorizedUser';
const userInfoID = "user_info";


showUserInfo();

function showUserInfo(user) {
    fetch(getAuthorizedUser)
        .then(response => response.json())
        .then(user => {
            let tBody = document.getElementById(userInfoID);
            tBody.innerHTML = "";

            let newRow = tBody.insertRow(0);
            let cell0 = newRow.insertCell(0);
            cell0.innerHTML = user.id;
            let cell1 = newRow.insertCell(1);
            cell1.innerHTML = user.firstName;
            let cell2 = newRow.insertCell(2);
            cell2.innerHTML = user.email;
            let cell3 = newRow.insertCell(3);
            cell3.innerHTML = listRoles(user).textContent;
        });

}