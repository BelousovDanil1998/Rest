showHeader();

function showHeader() {
    fetch('http://localhost:8080/getAuthorizedUser')
        .then(response => response.json())
        .then(user => {


            document.getElementById("header_email").innerHTML = user.email;

            let rolesList = document.createElement('ul');
            for (let i = 0; i < user.roleSet.length; i++) {
                let role = document.createElement('li');
                role.textContent = user.roleSet[i].name + " ";
                rolesList.appendChild(role);
            }
            document.getElementById("header_roles").innerHTML = 'with roles: ' + rolesList.textContent;
        });
}