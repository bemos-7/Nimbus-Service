from uuid import UUID

from pydantic import BaseModel


class UserRegisterResponse(BaseModel):
    id: UUID
