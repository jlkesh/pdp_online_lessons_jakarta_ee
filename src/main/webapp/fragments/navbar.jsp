<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <a class="navbar-brand" href="/book/list">Navbar</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
                aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav me-auto">
                <% if ("ADMIN".equals(session.getAttribute("role"))) {%>
                <li class="nav-item m-1">
                    <a href="/admin/book/create" class="btn btn-danger">Add Book</a>
                </li>
                <% } %>
            </ul>
            <ul class="navbar-nav">
                <% if (session.getAttribute("id") == null) {%>
                <li class="nav-item m-1">
                    <a href="/auth/login" class="btn btn-success">Login</a>
                </li>
                <li class="nav-item m-1">
                    <a href="/auth/register" class="btn btn-primary">Register</a>
                </li>
                <% } %>
                <% if (session.getAttribute("id") != null) {%>
                <li class="nav-item m-1">
                    <a href="/auth/logout" class="btn btn-danger">Logout</a>
                </li>
                <% } %>
            </ul>
        </div>
    </div>
</nav>