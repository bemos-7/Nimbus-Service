import os

from fastapi import HTTPException


async def list_files(self, user_folder: str) -> list:
    user_dir = self._get_user_dir(user_folder)
    try:
        print(user_dir)
        return os.listdir(user_dir)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Ошибка чтения директории: {e}")
