from app.api.http.v1.files import files_router
from app.api.http.v1.users import users_router

routes = (users_router, files_router)
