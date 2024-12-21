import os

import aiofiles
from fastapi import HTTPException


async def delete_file(self, user_folder: str, filename: str) -> str:
    user_dir = self._get_user_dir(user_folder)
    try:
        user_dir = self._get_user_dir(user_folder)
        file_path = os.path.join(user_dir, filename)
        if not os.path.exists(file_path):
            raise HTTPException(status_code=404, detail="Файл не найден")
        os.remove(file_path)
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Ошибка удаления файла: {e}")
