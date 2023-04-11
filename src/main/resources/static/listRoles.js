function listRoles(user) {
    let rolesList = document.createElement('ul');

    for (let i = 0; i < user.roleSet.length; i++) {
        let role = document.createElement('li');
        role.textContent = user.roleSet[i].name + " ";
        rolesList.appendChild(role);
    }

    return rolesList;
}