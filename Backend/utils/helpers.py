from google import genai
from google.genai import types
import json
import os
from dotenv import load_dotenv
api_key = os.getenv("API_KEY")
def llm_response_fetcher(prompt):
    client = genai.Client(api_key="AIzaSyDVNknjFgOwZjM2gy-8_s6mj9nZO57XG-Y")

    response = client.models.generate_content(
        model="gemini-2.5-flash",
        contents=prompt
    )
    print(response.text)