from uuid import uuid4

from app.models.v1.user import UserRegisterResponse
from passlib.hash import argon2


async def create_one(self) -> UserRegisterResponse:
    return UserRegisterResponse(id=uuid4())
