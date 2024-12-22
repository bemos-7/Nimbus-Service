from app.models.v1.user import UserRegisterResponse
from app.repositories.providers import provide_user_repository_stub
from app.repositories.user import UserRepository
from fastapi import APIRouter, Body, Depends

users_router = APIRouter(tags=["Users"], prefix="/nimbus/api/v1/users")


@users_router.get("/register", response_model=UserRegisterResponse, status_code=201)
async def create_one(user_repository: UserRepository = Depends(provide_user_repository_stub)):
    return await user_repository.create_one()
